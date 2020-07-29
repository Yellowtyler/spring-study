package com.study.service;

import com.study.SessionFactory;
import com.study.model.Consumer;
import com.study.model.Product;
import com.study.repository.ConsumerRepository;
import com.study.repository.ProductRepository;
import org.hibernate.Session;

import java.util.List;
import java.util.stream.Collectors;

public class ConsumerProductService {

    private final ConsumerRepository consumerRepository;
    private final ProductRepository productRepository;

    public ConsumerProductService(ConsumerRepository consumerRepository, ProductRepository productRepository) {
        this.consumerRepository = consumerRepository;
        this.productRepository = productRepository;
    }

    public List<Product> showProductsByConsumer(String name) {
        Consumer consumer = consumerRepository.findByName(name);
        return consumer!=null ? consumer.getProducts() : null;
    }

    public List<Consumer> showConsumersByProductTitle(String title) {
        Product product = productRepository.findByName(title);
        return product!=null ? product.getConsumers() : null;
    }

    public boolean deleteConsumer(String name) {
        return consumerRepository.deleteConsumer(name);
    }

    public boolean deleteProduct(String title) {
        return productRepository.deleteProduct(title);
    }

    public int buy(Long consumer_id, Long product_id) {
        Consumer consumer = consumerRepository.findById(consumer_id);
        Product product = productRepository.findById(product_id);
        if(consumer!=null && product!=null) {
            Session session = SessionFactory.getSession();
            session.beginTransaction();

            List<Product> productsOfConsumer = consumer
                    .getProducts()
                    .stream()
                    .filter(p->p.getId()!=product_id)
                    .collect(Collectors.toList());
            List<Consumer> consumersOfProduct = product
                    .getConsumers()
                    .stream()
                    .filter(c->c.getId()!=consumer_id)
                    .collect(Collectors.toList());

            product.setConsumers(consumersOfProduct);
            consumer.setProducts(productsOfConsumer);

            session.merge(consumer);
            session.merge(product);
            session.createNativeQuery("UPDATE consumers_products t2\n" +
                    "SET    product_price = t1.price\n" +
                    "FROM   products t1\n" +
                    "WHERE  t2.products_id = t1.id;")
                    .executeUpdate();
            session.getTransaction().commit();
            session.close();
            return 1;
        }
        else if(consumer!=null && product==null) {
            return 2;
        }
        else if(consumer==null && product!=null) {
            return 3;
        } else {
            return 4;
        }
    }
}

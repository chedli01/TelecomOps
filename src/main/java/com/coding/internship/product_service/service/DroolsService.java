package com.coding.internship.product_service.service;

import com.coding.internship.product_service.enums.ProductCategory;
import com.coding.internship.product_service.model.Product;
import lombok.Data;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class DroolsService {

    private final KieContainer kieContainer;


    public Product applyDiscount(Product product) {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");
        try {
            kieSession.insert(product);
            kieSession.fireAllRules();
            return product;
        } finally {
            kieSession.dispose();
        }
    }

    public List<Product> applyDiscountForCategory(List<Product> products, String category) {
        KieSession kieSession = kieContainer.newKieSession("ksession-rules");

        try {
            // Track modified products
            List<Product> modifiedProducts = new ArrayList<>();

            // Set global before inserting facts
            kieSession.setGlobal("targetCategory", category);
            kieSession.setGlobal("modifiedProducts", modifiedProducts);

            // Insert all products
            products.forEach(kieSession::insert);

            // Fire rules
            kieSession.fireAllRules();

            return modifiedProducts;
        } finally {
            kieSession.dispose();
        }
    }
}

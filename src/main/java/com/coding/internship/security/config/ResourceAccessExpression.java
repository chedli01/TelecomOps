package com.coding.internship.security.config;

import com.coding.internship.order.model.Order;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.user.admin.model.Admin;
import com.coding.internship.user.client.model.Client;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component( "resourceAccess")
public class ResourceAccessExpression {

    public boolean hasAccessToResource(Object resource){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Admin) {
            return true;
        }
        if (principal instanceof Client clientPrincipal) {
            if (resource instanceof Subscription subscription) {
                return clientPrincipal.getId().equals(subscription.getClient().getId());
            }
            if (resource instanceof Order order) {
                return clientPrincipal.getId().equals(order.getClient().getId());
            }

        }
        return false;
    }

}

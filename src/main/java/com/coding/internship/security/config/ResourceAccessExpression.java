package com.coding.internship.security.config;

import com.coding.internship.exception.RessourceNotFoundException;
import com.coding.internship.order.model.Order;
import com.coding.internship.subscription.model.Subscription;
import com.coding.internship.user.admin.model.Admin;
import com.coding.internship.user.client.model.Client;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component( "resourceAccess")
public class ResourceAccessExpression {

    public boolean hasAccessToResource(Object resource) throws NoSuchFieldException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof Admin) {
            return true;
        }
        if (principal instanceof Client) {
            Client clientPrincipal = (Client) principal;
            if (resource instanceof Subscription) {
                Subscription subscription = (Subscription) resource;
                return clientPrincipal.getId().equals(subscription.getClient().getId());
            }
            if (resource instanceof Order) {
                Order order = (Order) resource;
                return clientPrincipal.getId().equals(order.getClient().getId());
            }

        }
        return false;
    }

}

package at.irian.jsfatwork.gui.page;

import at.irian.jsfatwork.domain.Address;
import at.irian.jsfatwork.service.CustomerService;
import org.apache.myfaces.extensions.cdi.core.api.config.view.ViewConfig;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.Conversation;
import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ConversationScoped
public class AddCustomerBean extends CustomerBeanBase {

    @Inject
    private CustomerService customerService;
    @Inject
    private Conversation conversation;

    private Address address;

    @PostConstruct
    public void createCustomer() {
        super.init();

        if (customer == null) {
            customer = customerService.createNew();
            address = customerService.createAddress();
            customer.addAddress(address);
        }
    }

    public Class<? extends ViewConfig> goToStep1() {
        return View.AddCustomer1.class;
    }

    public Class<? extends ViewConfig> goToStep2() {
        return View.AddCustomer2.class;
    }

    public Class<? extends ViewConfig> save() {
        customerService.save(customer);
        conversation.close();
        return View.CustomerList.class;
    }

    public Class<? extends ViewConfig> cancel() {
        conversation.close();
        return View.CustomerList.class;
    }

    public Address getAddress() {
        return address;
    }

}
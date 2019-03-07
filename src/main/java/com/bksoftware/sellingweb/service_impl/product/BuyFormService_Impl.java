package com.bksoftware.sellingweb.service_impl.product;

import com.bksoftware.sellingweb.entities.product.BuyForm;
import com.bksoftware.sellingweb.repository.product.BuyFormRepository;
import com.bksoftware.sellingweb.service.product.BuyFormService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class BuyFormService_Impl implements BuyFormService {

    private final static Logger LOGGER = Logger.getLogger(FeedbackService_Impl.class.getName());

    private final BuyFormRepository buyFormRepository;

    public BuyFormService_Impl(BuyFormRepository buyFormRepository) {
        this.buyFormRepository = buyFormRepository;
    }

    @Override
    public List<BuyForm> findAllUnCheckBuyForm() {
        try {
            return buyFormRepository.findAllByChecked(false);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-uncheck-buyForm-error {0}", ex.getMessage());
        }
        return null;
    }

    @Override
    public boolean checkBuyForm(BuyForm buyForm) {
        try {
            buyForm.setChecked(true);
            buyFormRepository.save(buyForm);
            return true;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "find-all-uncheck-buyForm-error {0}", ex.getMessage());
            return false;
        }
    }
}

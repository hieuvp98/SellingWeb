package com.bksoftware.sellingweb.service.homepage;

import com.bksoftware.sellingweb.entities.homepage.FooterMenu;
import com.bksoftware.sellingweb.entities.homepage.FooterMenuDetails;
import org.springframework.stereotype.Service;

@Service
public interface FooterMenuService {

    boolean saveFooterMenu(FooterMenu footerMenu);

    boolean deleteFooterMenu(FooterMenu footerMenu);

    boolean saveFooterMenuDetails(FooterMenuDetails footerMenuDetails);

    boolean deleteFooterMenuDetails(FooterMenuDetails footerMenuDetails);
}

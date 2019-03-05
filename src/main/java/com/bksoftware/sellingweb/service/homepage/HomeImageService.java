package com.bksoftware.sellingweb.service.homepage;

import com.bksoftware.sellingweb.entities.homepage.HomeImage;
import org.springframework.stereotype.Service;

@Service
public interface HomeImageService {

    boolean saveHomeImage(HomeImage homeImage);

    boolean deleteHomeImage(HomeImage homeImage);
}

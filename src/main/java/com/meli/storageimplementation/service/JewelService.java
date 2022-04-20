package com.meli.storageimplementation.service;

import com.meli.storageimplementation.models.Jewel;
import com.meli.storageimplementation.repository.JewelRepository;
import org.springframework.stereotype.Service;

@Service
public class JewelService {

    private final JewelRepository jewelRepository;

    public JewelService(JewelRepository jewelRepository) {
        this.jewelRepository = jewelRepository;
    }

    public Jewel createJewel(Jewel jewel) {
        return jewelRepository.save(jewel);
    }

}

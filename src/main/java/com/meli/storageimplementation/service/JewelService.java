package com.meli.storageimplementation.service;

import com.meli.storageimplementation.errors.JewelNotFoundException;
import com.meli.storageimplementation.models.Jewel;
import com.meli.storageimplementation.repository.JewelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JewelService {

    private final JewelRepository jewelRepository;

    public JewelService(JewelRepository jewelRepository) {
        this.jewelRepository = jewelRepository;
    }

    public Jewel createJewel(Jewel jewel) {
        return jewelRepository.save(jewel);
    }

    public Jewel findJewelById(UUID jewelId) throws JewelNotFoundException {
        Optional<Jewel> jewel = jewelRepository.findById(jewelId);
        if (jewel.isPresent()) {
            return jewel.get();
        }

        throw new JewelNotFoundException("Jewel does not exists");
    }

    public List<Jewel> listJewels() {
        return jewelRepository.findAll();
    }

}

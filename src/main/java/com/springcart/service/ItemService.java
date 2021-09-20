package com.springcart.service;

import com.springcart.dto.ItemFormDto;
import com.springcart.entity.Item;
import com.springcart.entity.ItemImg;
import com.springcart.repository.ItemImgRepository;
import com.springcart.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ItemService {
    private final ItemRepository itemRepository;
    private final ItemImgService itemImgService;
    private final ItemImgRepository itemImgRepository;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        for(int i=0; i<itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if(i == 0) itemImg.setRepimgYn("Y");
            else itemImg.setRepimgYn("N");

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }
}

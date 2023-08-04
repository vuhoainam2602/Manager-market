package project_mart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;
import project_mart.model.PointCard;
import project_mart.model.Supplier;
import project_mart.repository.PointCardRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PointCardService {
    @Autowired
    private PointCardRepository pointCardRepository;

    public List<PointCard> findAll(){
        return pointCardRepository.findAll();
    }

    public PointCard add(PointCard pointCard){
        boolean checkSup= Optional.ofNullable(pointCard)
                .filter(t -> !StringUtils.isEmpty(t.getTenKH()))
                .filter(t -> !StringUtils.isEmpty(t.getSdt()))
                .filter(t -> !StringUtils.isEmpty(t.getCccd()))
                .isPresent(); // Trả về true nếu hợp lệ, ngược lại false

        if (checkSup){
            pointCard.setCheck(true);
            pointCard.setDiem(0);
            return pointCardRepository.save(pointCard);
        }
        return null;
    }

    public PointCard findByID(Long id){
        return pointCardRepository.findById(id).orElse(null);
    }

    public PointCard update(PointCard pointCard){
        pointCard.setCheck(true);
        return pointCardRepository.save(pointCard);
    }

    public void deleteById(Long id){
        PointCard fromDB = pointCardRepository.findById(id).orElse(null);
        if(fromDB == null){
            return;
        }

        fromDB.setCheck(false);
        pointCardRepository.save(fromDB);
    }

    // mua hàng thì +1
    public void increasePoint(Long id){
        PointCard fromDB = pointCardRepository.findById(id).orElse(null);
        if(fromDB == null){
            return;
        }

        fromDB.setDiem(fromDB.getDiem()+1);
        pointCardRepository.save(fromDB);
    }

    public List<PointCard> search(String keyword){
        return  pointCardRepository.search(keyword);
    }
}

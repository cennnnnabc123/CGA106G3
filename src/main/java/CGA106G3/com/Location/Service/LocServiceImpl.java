package CGA106G3.com.Location.Service;

import CGA106G3.com.Location.DTO.LocDTO;
import CGA106G3.com.Location.Entity.Loc;
import CGA106G3.com.Location.Repository.LocRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;




@Service
public class LocServiceImpl implements LocService {


    @Autowired
    private LocRepository locRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public LocDTO updateLoc(LocDTO locDTO){
        Loc loc = modelMapper.map(locDTO,Loc.class);
         locRepository.save(loc);
         return locDTO;

    }

    @Override
    public Optional<Loc> getOneLoc(Integer locno){
        return locRepository.findById(locno);
    }

    @Override
    public List<LocDTO> getAllLoc() {

        return locRepository.findAll()
                .stream()
                .map(this::EntityToDTO)
                .collect(Collectors.toList());
    }
    private LocDTO EntityToDTO(Loc loc){
        LocDTO locDTO;
        locDTO = modelMapper.map(loc, LocDTO.class);
        return locDTO;
    }

    @Override
    public boolean update(Loc loc) {
        return false;
    }

}

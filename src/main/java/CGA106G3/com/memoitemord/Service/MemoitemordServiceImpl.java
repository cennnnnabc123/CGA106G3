package CGA106G3.com.memoitemord.Service;

import CGA106G3.com.memoitemord.DTO.CartDTO;
import CGA106G3.com.memoitemord.DTO.ItemDTO;
import CGA106G3.com.memoitemord.DTO.MemoitemordDTO;
import CGA106G3.com.memoitemord.DTO.MemoritemordOrderDTO;
import CGA106G3.com.memoitemord.Entity.Memoitemord;
import CGA106G3.com.memoitemord.Repository.MemoitemOrdDetailRepository;
import CGA106G3.com.memoitemord.Repository.MemoitemordRepository;
import CGA106G3.com.memoorddetail.DTO.MemoorddetailDTO;
import CGA106G3.com.memoorddetail.Entity.Memoorddetail;
import CGA106G3.com.memoorddetail.Repository.MemoorddetailRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemoitemordServiceImpl implements MemoitemordService{

    @Autowired
    private MemoitemordRepository memoitemordRepository;

    @Autowired
    private MemoitemOrdDetailRepository memoitemOrdDetailRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private MemoorddetailRepository memoorddetailRepository;
    @Override
    public List<MemoitemordDTO> getAllMemoitemord(){
        return memoitemordRepository.findAll()
                .stream()
                .map(this:: EntityToDTo)
                .collect(Collectors.toList());
    }
    public List<MemoorddetailDTO> getDetailByordno(Integer ordno){
        return memoorddetailRepository.findByOrdno(ordno)
                .stream()
                .map(this::EntityToDTO3)
                .collect(Collectors.toList());
    }
    public MemoitemordDTO updateMemoitemord(MemoitemordDTO memoitemordDTO){
        Memoitemord memoitemord = modelMapper.map(memoitemordDTO,Memoitemord.class);
        memoitemord.setOrddate(LocalDateTime.now());
         memoitemordRepository.save(memoitemord);
         return memoitemordDTO;
    }

    public MemoorddetailDTO EntityToDetail(Memoitemord memoitemord){
        MemoorddetailDTO memoorddetailDTO = new MemoorddetailDTO();
        memoorddetailDTO = modelMapper.map(memoitemord,MemoorddetailDTO.class);
        return memoorddetailDTO;
    }
    @Transactional
    public CartDTO addMemoitemord(CartDTO cartDTO){
        Memoitemord memoitemord = new Memoitemord();
        memoitemord.setMembno(cartDTO.getMembno());
        memoitemord.setTotalpr(cartDTO.getTotalpr());
        memoitemord.setOrddate(LocalDateTime.now());
        memoitemord.setOrdsta(cartDTO.getOrdsta());
        memoitemord.setPaysta(cartDTO.getPaysta());
        memoitemord.setRec(cartDTO.getRec());
        memoitemord.setRecaddr(cartDTO.getRecaddr());
        Memoitemord ordno = memoitemordRepository.save(memoitemord);
        for (ItemDTO item : cartDTO.getItems()){
            Memoorddetail memoorddetail = new Memoorddetail();
            memoorddetail.setOrdno(ordno.getOrdno());
            memoorddetail.setMino(Integer.valueOf(item.getNo()));
            memoorddetail.setMiqty(Integer.valueOf(item.getQuantity()));
            memoorddetail.setMidate(LocalDateTime.now());
            memoorddetail.setMiprice(item.getPrice());
            memoorddetailRepository.save(memoorddetail);
        }
        return EntityToDTo2(memoitemord);
    }
    private MemoitemordDTO EntityToDTo(Memoitemord memoitemord){
        MemoitemordDTO memoitemordDTO = new MemoitemordDTO();
        memoitemordDTO = modelMapper.map(memoitemord,MemoitemordDTO.class);
        return memoitemordDTO;
    }

@Override

     public Memoitemord findDetailByOrdno(Integer ordno){
        return  memoitemordRepository.findById(ordno).orElse(null);
     }

    @Override
    public List<MemoitemordDTO> findByMemb(Integer memeno) {
        return memoitemordRepository.findByMembno(memeno)
                .stream().map(this::EntityToDTo)
                .collect(Collectors.toList());
    }
    public List<MemoritemordOrderDTO> ordObjecttoDTO (List<Object[]> details){
        List<MemoritemordOrderDTO>detailDTO=new ArrayList<>();
        for(Object[] detail: details){
            MemoritemordOrderDTO dto = new MemoritemordOrderDTO();
            dto.setMino((Integer) detail[0]);
            dto.setMiname((String) detail[1]);
            dto.setMiprice((Integer) detail[2]);
            dto.setMicateno((Integer) detail[3]);
            dto.setMiqty((Integer)detail[4]);
            dto.setMidate((Date) detail[5]);
            dto.setOrdno((Integer)  detail[6]);
            dto.setMembno((Integer) detail[7]);
            dto.setTotalPr((Integer) detail[8]);
            dto.setOrddate((Date) detail[9]);
            dto.setOrdsta((Integer) detail[10]);
            dto.setPaysta((Integer) detail[11]);
            dto.setRec((String) detail[12]);
            dto.setRecaddr((String)detail[13]);
            detailDTO.add(dto);
        }
        return  detailDTO;
    }
//    訂單編號查詢
    @Override
    public List<MemoritemordOrderDTO> findAllMemoitemordDetailbyOrderno(Integer ordno) {
        List<Object[]> detail = memoitemOrdDetailRepository.findAllMemoitemordDetailbyOrderno(ordno);
        if(detail.isEmpty()){
            return new ArrayList<>();
        }
        return ordObjecttoDTO(detail);
    }


    //    會員編號查詢
    @Override
    public List<MemoritemordOrderDTO> findAllMemoitemordDetailbyMembno(Integer membno) {
        List<Object[]> detail = memoitemOrdDetailRepository.findAllMemoitemordDetailbyMembno(membno);
        return ordObjecttoDTO(detail);


    }
    private CartDTO EntityToDTo2(Memoitemord memoitemord){
        return modelMapper.map(memoitemord,CartDTO.class);
    }
    private MemoorddetailDTO EntityToDTO3(Memoorddetail memoorddetail){
        MemoorddetailDTO memoorddetailDTO = new MemoorddetailDTO();
        memoorddetailDTO = modelMapper.map(memoorddetail,MemoorddetailDTO.class);
        return memoorddetailDTO;
    }
}

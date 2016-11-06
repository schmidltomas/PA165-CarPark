package cz.muni.fi.pa165.carpark.service.utils.mapper;


import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by karelfajkus on 06/11/2016.
 */
@Service
public interface ClassMapper {

    public <T> T mapTo(Object from, Class<T> to);

    public <T> List<T> mapTo(Collection<?> from, Class<T> to);

    public Mapper getMapper();
}

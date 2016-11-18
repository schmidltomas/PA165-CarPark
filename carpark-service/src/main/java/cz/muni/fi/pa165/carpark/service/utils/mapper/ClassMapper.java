package cz.muni.fi.pa165.carpark.service.utils.mapper;


import org.dozer.Mapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * Created by karelfajkus on 06/11/2016.
 */
public interface ClassMapper {

    <T> T mapTo(Object from, Class<T> to);

    <T> List<T> mapTo(Collection<?> from, Class<T> to);

    Mapper getMapper();
}

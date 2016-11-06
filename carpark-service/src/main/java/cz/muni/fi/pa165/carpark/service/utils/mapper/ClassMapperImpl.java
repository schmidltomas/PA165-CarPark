package cz.muni.fi.pa165.carpark.service.utils.mapper;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by karelfajkus on 06/11/2016.
 */
@Service
public class ClassMapperImpl implements ClassMapper {

    @Autowired
    private Mapper dozer;

    @Override
    public <T> T mapTo(Object from, Class<T> to) {
        return dozer.map(from, to);
    }

    @Override
    public <T> List<T> mapTo(Collection<?> from, Class<T> to) {
        return from.stream()
                .map(object -> dozer.map(object, to))
                .collect(Collectors.toList());
    }

    @Override
    public Mapper getMapper() {
        return dozer;
    }
}

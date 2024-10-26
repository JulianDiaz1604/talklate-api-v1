package co.edu.uco.talklate.util.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GenericMapper {

    /**
     * Mapea un objeto de origen a un objeto de destino del tipo dado.
     *
     * @param <S> Tipo del objeto fuente
     * @param <D> Tipo del objeto destino
     * @param source Objeto fuente desde el cual se van a copiar las propiedades
     * @param destinationType Clase del objeto destino
     * @return Objeto mapeado al tipo de destino
     */
    public <S, D> D map(S source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        try {
            D destination = destinationType.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, destination);
            return destination;
        } catch (Exception e) {
            throw new RuntimeException("Error al mapear el objeto", e);
        }
    }

    /**
     * Mapea una lista de objetos de origen a una lista de objetos de destino del tipo dado.
     *
     * @param <S> Tipo del objeto fuente
     * @param <D> Tipo del objeto destino
     * @param sourceList Lista de objetos fuente desde la cual se van a copiar las propiedades
     * @param destinationType Clase del objeto destino
     * @return Objeto mapeado al tipo de destino
     */
    public <S, D> ArrayList<D> mapList(List<S> sourceList, Class<D> destinationType) {
        if (sourceList == null || sourceList.isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList<D> destinationList = new ArrayList<>();
        for (S source : sourceList) {
            D destination = map(source, destinationType);
            destinationList.add(destination);
        }
        return destinationList;
    }

}

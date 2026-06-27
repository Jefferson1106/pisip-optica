package com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.mapeadores;

import org.mapstruct.Mapper;

import com.uisrael.opticaperfectvisionapi.dominio.entidades.DetalleCatalogo;
import com.uisrael.opticaperfectvisionapi.infraestructura.persistencia.jpa.DetalleCatalogoEntity;

@Mapper (componentModel = "spring")
public interface IDetalleCatalogoJpaMapper {
DetalleCatalogo toDomain (DetalleCatalogoEntity entity);
DetalleCatalogoEntity toEntity (DetalleCatalogo detalleCatalogoPojo);

}

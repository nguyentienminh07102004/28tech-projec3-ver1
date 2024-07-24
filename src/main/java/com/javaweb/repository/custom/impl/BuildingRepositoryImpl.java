package com.javaweb.repository.custom.impl;

import com.javaweb.Builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.custom.BuildingRepositoryCustomer;
import com.javaweb.utils.NumberUtils;
import com.javaweb.utils.StringUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustomer {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder builder) {
        StringBuilder sql = new StringBuilder("SELECT b.* FROM building b ");
        joinTable(sql, builder);
        sql.append(" WHERE 1 = 1 ");
        where_normal(sql, builder);
        where_special(sql, builder);
        sql.append(" GROUP BY b.id");
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();
    }

    private void joinTable(StringBuilder sql, BuildingSearchBuilder builder) {
        if(builder.getStaffId() != null) {
            sql.append(" INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
        }
        if(builder.getAreaFrom() != null || builder.getAreaTo() != null) {
            sql.append(" INNER JOIN rentarea ON b.id = rentarea.buildingid ");
        }
    }

    private void where_normal(StringBuilder sql, BuildingSearchBuilder builder) {
        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                String key = field.getName().trim();
                if (!key.toLowerCase().startsWith("area") && !key.equalsIgnoreCase("typeCode")
                        && !key.toLowerCase().startsWith("rentprice")
                        && !key.toLowerCase().equalsIgnoreCase("staffId")) {
                    String value = field.get(builder) == null ? null : field.get(builder).toString();
                    if(StringUtils.check(value)) {
                        if(NumberUtils.isNumber(value)) {
                            sql.append(" AND b.").append(key).append(" = ").append(value);
                        } else {
                            sql.append(" AND b.").append(key).append(" LIKE '%").append(value).append("%' ");
                        }
                    }
                }
            }
        } catch (IllegalAccessException exception) {

        }
    }

    private void where_special(StringBuilder sql, BuildingSearchBuilder builder) {
        if(builder.getAreaFrom() != null || builder.getAreaTo() != null) {
            sql.append(" AND EXISTS ( SELECT rentarea.value FROM rentarea WHERE b.id = rentarea.buildingid ");
            if(builder.getAreaFrom() != null) {
                sql.append(" AND b.rentarea >= ").append(builder.getAreaFrom());
            }
            if (builder.getAreaTo() != null) {
                sql.append(" AND b.rentarea <= ").append(builder.getAreaTo()).append(" ) ");
            }
        }
        if(builder.getStaffId() != null) {
            sql.append(" AND assignmentbuilding.staffid = ").append(builder.getStaffId());
        }
        if(builder.getRentPriceFrom() != null) {
            sql.append(" AND b.rentprice >= ").append(builder.getRentPriceFrom());
        }
        if (builder.getRentPriceTo() != null) {
            sql.append(" AND b.rentprice <= ").append(builder.getRentPriceTo());
        }
        if(builder.getTypeCode() != null) {
            List<String> typeCode = builder.getTypeCode();
            String type = typeCode.stream()
                    .map(item -> "'%" + item + "%'")
                    .map(item -> "b.type LIKE " + item)
                    .collect(Collectors.joining(" OR "));
            type = "( " + type + " ) ";
            sql.append(" AND ").append(type);
        }
    }
}

package com.acupt.dao;

import com.acupt.entity.Sickness;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by liujie on 2017/9/4.
 */
public interface SicknessDAO extends JpaRepository<Sickness, Long> {

}

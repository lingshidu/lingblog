package cn.ling.service.impl;

import cn.ling.entity.VisitRecord;
import cn.ling.mapper.VisitRecordMapper;
import cn.ling.service.VisitRecordService;
import org.springframework.stereotype.Service;

/**
 * 访问记录业务层实现
 */
@Service
public class VisitRecordServiceImpl implements VisitRecordService {
	private final VisitRecordMapper visitRecordMapper;

	public VisitRecordServiceImpl(VisitRecordMapper visitRecordMapper) {
		this.visitRecordMapper = visitRecordMapper;
	}

	@Override
	public void saveVisitRecord(VisitRecord visitRecord) {
		visitRecordMapper.saveVisitRecord(visitRecord);
	}
}

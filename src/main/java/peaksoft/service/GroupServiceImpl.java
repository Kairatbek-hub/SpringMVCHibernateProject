package peaksoft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.dao.GroupDao;
import peaksoft.model.Group;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupDao groupDao;

    @Override
    public void saveGroup(Group group) {
        groupDao.saveGroup(group);
    }

    @Override
    public List<Group> groupList() {
        return groupDao.groupList();
    }

    @Override
    public void updateGroup(Group group) {
        groupDao.updateGroup(group);
    }

    @Override
    public void deleteGroup(Long id) {
        groupDao.deleteGroup(id);
    }

    @Override
    public Group getGroupId(Long id) {
        return groupDao.getGroupId(id);
    }
}

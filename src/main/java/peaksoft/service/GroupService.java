package peaksoft.service;

import peaksoft.model.Group;

import java.util.List;

public interface GroupService {
    void saveGroup(Group group);
    List<Group> groupList();
    void updateGroup(Group group);
    void deleteGroup(Long id);
    Group getGroupId(Long id);
}

package survey.surveyspring.repository;

import survey.surveyspring.domain.Servay;

import java.util.*;

public class MemoryServayRepository implements ServayRepository{

    private ArrayList <Servay> member;

    @Override
    public Servay save(Servay servay) {
        member.add(servay);
        return servay;
    }

    @Override
    public Optional<Servay> findById(String userId) {
        return Optional.empty();
    }

    @Override
    public Optional<Servay> findByName(String userName) {
        return Optional.empty();
    }

    @Override
    public Optional<Servay> findByPw(String userPw) {
        return Optional.empty();
    }
}

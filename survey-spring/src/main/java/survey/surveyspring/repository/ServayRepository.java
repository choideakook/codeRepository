package survey.surveyspring.repository;

import survey.surveyspring.domain.Servay;

import java.util.List;
import java.util.Optional;

public interface ServayRepository {

    Servay save (Servay servay);
    Optional<Servay> findById(String userId);
    Optional<Servay> findByName(String userName);
    Optional<Servay> findByPw(String userPw);

}

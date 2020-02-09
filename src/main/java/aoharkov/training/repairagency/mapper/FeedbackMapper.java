package aoharkov.training.repairagency.mapper;

import aoharkov.training.repairagency.domain.Feedback;
import aoharkov.training.repairagency.domain.Request;
import aoharkov.training.repairagency.entity.FeedbackEntity;

public class FeedbackMapper implements Mapper<FeedbackEntity, Feedback> {
    @Override
    public FeedbackEntity mapDomainToEntity(Feedback item) {
        return FeedbackEntity.builder()
                .withId(item.getId())
                .withRequestId(item.getRequest().getId())
                .withScore(item.getScore())
                .withText(item.getText())
                .build();
    }

    @Override
    public Feedback mapEntityToDomain(FeedbackEntity entity) {
        return Feedback.builder()
                .withId(entity.getId())
                .withRequest(Request.builder().withId(entity.getRequestId()).build())
                .withScore(entity.getScore())
                .withText(entity.getText())
                .build();
    }
}
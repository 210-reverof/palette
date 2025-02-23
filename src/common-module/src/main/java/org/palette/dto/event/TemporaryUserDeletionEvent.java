package org.palette.dto.event;

import org.palette.dto.EaselEvent;

public record TemporaryUserDeletionEvent(String email) implements EaselEvent {

    @Override
    public String getTopic() {
        return TopicConstant.TEMPORARY_USER_DELETION.value;
    }
}

package com.wisenut.domain.common.event;

public interface DomainEventPublisher {
    void publish(DomainEvent event);
}

package com.crud.tasks.service;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TrelloServiceTest {

    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Mock
    private SimpleEmailService emailService;

    @Mock
    private AdminConfig adminConfig;

    @Test
    void shouldFetchTrelloBoards() {
        //Given
        List<TrelloBoardDto> trelloBoardDtos = List.of(new TrelloBoardDto("id", "test", new ArrayList<>()));
        when(trelloClient.getTrelloBoards()).thenReturn(trelloBoardDtos);
        //When
        List<TrelloBoardDto> fetchedTrelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertThat(fetchedTrelloBoardDtos).isNotNull();
        assertEquals(1, fetchedTrelloBoardDtos.size());
    }

    @Test
    void shouldFetchEmptyBoardList() {
        //Given
        when(trelloClient.getTrelloBoards()).thenReturn(List.of());
        //When
        List<TrelloBoardDto> fetchedTrelloBoardDtos = trelloService.fetchTrelloBoards();
        //Then
        assertThat(fetchedTrelloBoardDtos).isNotNull();
        assertEquals(0, fetchedTrelloBoardDtos.size());
    }

    @Test
    void shouldCreateTrelloCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("name", "description", "pos", "listId");
        CreatedTrelloCardDto createdTrelloCardDtoMock = new CreatedTrelloCardDto("id", "name", "shortUrl");
        when(trelloClient.createNewCard(trelloCardDto)).thenReturn(createdTrelloCardDtoMock);
        //When
        CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
        //Then
        assertThat(createdTrelloCardDto).isNotNull();
        assertEquals("id", createdTrelloCardDto.getId());
        assertEquals("name", createdTrelloCardDto.getName());
        assertEquals("shortUrl", createdTrelloCardDto.getShortUrl());
    }
}
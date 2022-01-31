package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        TrelloBoardDto trelloBoardDto1 = new TrelloBoardDto("1", "board1", new ArrayList<>());
        TrelloBoardDto trelloBoardDto2 = new TrelloBoardDto("2", "board2", new ArrayList<>());
        List<TrelloBoardDto> trelloBoardDtos = new ArrayList<>();
        trelloBoardDtos.add(trelloBoardDto1);
        trelloBoardDtos.add(trelloBoardDto2);
        //When
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloBoardDtos);
        //Then
        assertEquals(2,trelloBoards.size());
        assertEquals("1",trelloBoards.get(0).getId());
        assertEquals("board2",trelloBoards.get(1).getName());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        TrelloBoard trelloBoard1 = new TrelloBoard("1", "board1", new ArrayList<>());
        TrelloBoard trelloBoard2 = new TrelloBoard("2", "board2", new ArrayList<>());
        List<TrelloBoard> trelloBoards = new ArrayList<>();
        trelloBoards.add(trelloBoard1);
        trelloBoards.add(trelloBoard2);
        //When
        List<TrelloBoardDto> trelloBoardDtos = trelloMapper.mapToBoardsDto(trelloBoards);
        //Then
        assertEquals(2,trelloBoardDtos.size());
        assertEquals("1",trelloBoardDtos.get(0).getId());
        assertEquals("board2",trelloBoardDtos.get(1).getName());
    }

    @Test
    public void shouldMapToList() {
        //Given
        TrelloListDto trelloListDto = new TrelloListDto("1", "list", true);
        List<TrelloListDto> trelloListDtos = new ArrayList<>();
        trelloListDtos.add(trelloListDto);
        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtos);
        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("list", trelloLists.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed());

    }

    @Test
    public void shouldMapToListDto() {
        //Given
        TrelloList trelloList = new TrelloList("1", "list", true);
        List<TrelloList> trelloLists = new ArrayList<>();
        trelloLists.add(trelloList);
        //When
        List<TrelloListDto> trelloListDtos = trelloMapper.mapToListDto(trelloLists);
        //Then
        assertEquals(1, trelloListDtos.size());
        assertEquals("list", trelloListDtos.get(0).getName());
        assertTrue(trelloListDtos.get(0).isClosed());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("card", "description", "pos", "1");
        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        //Then
        assertEquals("card", trelloCard.getName());
        assertEquals("description", trelloCard.getDescription());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("card", "description", "pos", "1");
        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        //Then
        assertEquals("card", trelloCardDto.getName());
        assertEquals("description", trelloCardDto.getDescription());
    }
}

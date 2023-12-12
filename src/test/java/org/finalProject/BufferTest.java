package org.finalProject;

import org.finalProject.helpers.BufferHelper;

import org.finalProject.mockModels.MockConsumer;
import org.finalProject.mockModels.MockProducer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;



class BufferTest {

    BufferHelper buffer;
    Item item;
    MockConsumer mockConsumer;
    MockProducer mockProducer;

    @BeforeEach
    void init() {
        item = new Item("buffer_test_item");
        buffer = new BufferHelper();
        mockConsumer = new MockConsumer(buffer);
        mockProducer = new MockProducer(buffer);
    }

    @Test
    @DisplayName("Test buffer list is a LinkedList")
    public void testBufferListIsLinkedList() {
        assertTrue(buffer.getBuffer() instanceof LinkedList<Item>);
    }

    @Test
    @DisplayName("Test Buffer List Not Null & Is Empty")
    public void testBufferListNotNullIsEmpty() {
        assertNotNull(buffer.getBuffer());
        assertTrue(buffer.getBuffer().isEmpty());
    }

    @Test
    @DisplayName("Test mockProducer.produce() with valid item")
    void testBufferAddWithValidItem() {
        mockProducer.produce(item);
        assertFalse(buffer.getBuffer().isEmpty());
        assertEquals(item, buffer.getBuffer().peek());
    }

    @Test
    @DisplayName("Test mockProducer.produce() with null item")
    void testBufferAddWithNullItem() {
        mockProducer.produce(null);
        assertNull(buffer.getBuffer().peek());
    }

    @Test
    @DisplayName("Test mockConsumer.consume() non empty buffer")
    void testBufferRemoveWithMockConsumer() {
        mockProducer.produce(item);
        assertFalse(buffer.getBuffer().isEmpty());
        assertEquals(item, mockConsumer.consume());

    }

    @Test
    @DisplayName("Test mockConsumer.consume() on empty buffer will set thread state to wait()")
    void testRemoveFromEmptyBufferSetThreadStateToWait() throws InterruptedException {
        Thread t = new Thread(mockConsumer::consume);
        t.start();
        Thread.sleep(500);

        boolean isWaiting = t.getState() == Thread.State.WAITING;

        t.interrupt();
        t.join();

        assertTrue(isWaiting);
    }

    @Test
    @DisplayName("Test remove with empty buffer, wait(), add(new Item())")
    public void testRemoveWaitAndAddItemToEmptyBuffer() throws InterruptedException, ExecutionException {
        CompletableFuture<Item> futureResult = CompletableFuture.supplyAsync(mockConsumer::consume);

        Thread.sleep(1000);
        mockProducer.produce(item);
        Thread.sleep(1000);

        assertEquals(item, futureResult.get());


    }

    @Test
    @DisplayName("Test mockConsumer.consume() on empty buffer + .interrupt() throws InterruptedException")
    void testBufferInterruptWaitThrowsInterruptedException() throws InterruptedException {
        ByteArrayOutputStream errContent = new ByteArrayOutputStream();
        System.setErr(new PrintStream(errContent));
        Thread t = new Thread(mockConsumer::consume);
        t.start();

        do {
            Thread.sleep(50);

        } while (t.getState() != Thread.State.WAITING);

        t.interrupt();

        System.setErr(System.err);

        String errorMessage = errContent.toString();

        assertTrue(errorMessage.contains("java.lang.InterruptedException"));
        assertTrue(errorMessage.contains("at org.finalProject.Buffer.remove"));

    }
}






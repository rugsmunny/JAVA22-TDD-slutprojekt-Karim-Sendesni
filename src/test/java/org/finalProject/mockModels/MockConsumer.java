package org.finalProject.mockModels;

import org.finalProject.Consumer;
import org.finalProject.Item;
import org.finalProject.helpers.BufferHelper;

public class MockConsumer implements Consumer {

    BufferHelper bufferHelper;

    public MockConsumer(BufferHelper bufferHelper) {
        this.bufferHelper = bufferHelper;
    }
    public Item consume() {
        return bufferHelper.remove();
    }

    @Override
    public void run() {

    }

    @Override
    public void stopRunning() {

    }
}


package org.finalProject.mockModels;

import org.finalProject.Item;
import org.finalProject.Producer;
import org.finalProject.helpers.BufferHelper;

public class MockProducer implements Producer {

    BufferHelper bufferHelper;

    public MockProducer(BufferHelper bufferHelper) {
        this.bufferHelper = bufferHelper;
    }
    public boolean produce(Item item) {
        return bufferHelper.add(item);
    }

    @Override
    public void run() {}

    @Override
    public void stopRunning() {}
}


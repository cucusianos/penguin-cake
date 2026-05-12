package com.penguin.cake.takeout.adapter;
import com.penguin.cake.takeout.TakeoutPlatform;
import org.springframework.stereotype.Component;
@Component
public class MockTakeoutAdapter extends AbstractDemoTakeoutAdapter {
    public TakeoutPlatform platform() { return TakeoutPlatform.MOCK; }
}

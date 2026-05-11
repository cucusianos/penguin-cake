package com.penguin.cake.waimai.adapter;
import com.penguin.cake.waimai.WaimaiPlatform;
import org.springframework.stereotype.Component;
@Component
public class JdWaimaiAdapter extends AbstractDemoWaimaiAdapter {
    public WaimaiPlatform platform() { return WaimaiPlatform.JD_WAIMAI; }
}

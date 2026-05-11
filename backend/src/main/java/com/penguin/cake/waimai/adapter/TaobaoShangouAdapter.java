package com.penguin.cake.waimai.adapter;
import com.penguin.cake.waimai.WaimaiPlatform;
import org.springframework.stereotype.Component;
@Component
public class TaobaoShangouAdapter extends AbstractDemoWaimaiAdapter {
    public WaimaiPlatform platform() { return WaimaiPlatform.TAOBAO_SHANGOU; }
}

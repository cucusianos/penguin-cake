package com.penguin.cake.voucher;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.voucher.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vouchers")
public class VoucherController {
    private final VoucherService service;
    public VoucherController(VoucherService service) { this.service = service; }
    @PostMapping("/verify") public ApiResponse<VoucherVerifyResponse> verify(@Valid @RequestBody VoucherVerifyRequest request){ return ApiResponse.ok(service.verify(request)); }
    @PostMapping("/consume") public ApiResponse<VoucherVerifyResponse> consume(@Valid @RequestBody VoucherConsumeRequest request){ return ApiResponse.ok(service.consume(request)); }
    @GetMapping("/channels") public ApiResponse<VoucherChannel[]> channels(){ return ApiResponse.ok(VoucherChannel.values()); }
}

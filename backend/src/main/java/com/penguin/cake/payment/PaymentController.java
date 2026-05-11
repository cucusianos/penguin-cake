package com.penguin.cake.payment;
import com.penguin.cake.common.ApiResponse;
import com.penguin.cake.payment.dto.*;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final PaymentService service;
    public PaymentController(PaymentService service) { this.service = service; }
    @PostMapping("/create")
    public ApiResponse<PaymentCreateResponse> create(@Valid @RequestBody PaymentCreateRequest request) { return ApiResponse.ok(service.create(request)); }
    @PostMapping("/notify")
    public ApiResponse<Map<String, Object>> notify(@RequestBody PaymentNotifyRequest request) { return ApiResponse.ok(Map.of("verified", service.notify(request))); }
    @GetMapping("/providers")
    public ApiResponse<PaymentProvider[]> providers() { return ApiResponse.ok(PaymentProvider.values()); }
}

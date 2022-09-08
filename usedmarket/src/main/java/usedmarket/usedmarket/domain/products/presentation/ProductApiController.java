package usedmarket.usedmarket.domain.products.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import usedmarket.usedmarket.domain.products.presentation.dto.request.ProductRequestDto;
import usedmarket.usedmarket.domain.products.presentation.dto.request.ProductStatusRequestDto;
import usedmarket.usedmarket.domain.products.presentation.dto.response.ProductAllResponseDto;
import usedmarket.usedmarket.domain.products.presentation.dto.response.ProductDetailResponseDto;
import usedmarket.usedmarket.domain.products.service.ProductService;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductApiController {

    private final ProductService productService;

    @PostMapping("/new")
    public Long create(ProductRequestDto requestDto) throws IOException {
        return productService.createBoard(requestDto);
    }

    //TODO : 나중에 size 20으로 바꾸기
    @GetMapping("")
    public List<ProductAllResponseDto> findAll(@RequestParam(value = "page", defaultValue= "0") int pageNumber) {
        return productService.findAllBoard(pageNumber);
    }

    @GetMapping("/{id}")
    public ProductDetailResponseDto detail(@PathVariable("id") Long id) {
        return productService.detailBoard(id);
    }

    @PutMapping("/{id}")
    public void status(@PathVariable("id") Long id, @RequestBody ProductStatusRequestDto requestDto) {
        productService.statusBoard(id, requestDto);
    }

    @GetMapping("/search")
    public List<ProductAllResponseDto> search(@RequestParam("keyword") String keyword,
                                              @RequestParam(value = "page", defaultValue = "0") int pageNumber) {
        return productService.searchBoard(keyword, pageNumber);
    }

    @PutMapping("/{id}/edit")
    public Long update(@PathVariable("id") Long id, @ModelAttribute ProductRequestDto requestDto) throws IOException {
        return productService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public Long delete(@PathVariable("id") Long id) {
        return productService.deleteBoard(id);
    }
}

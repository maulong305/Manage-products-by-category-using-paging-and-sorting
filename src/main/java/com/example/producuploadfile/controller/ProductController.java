package com.example.producuploadfile.controller;

import com.example.producuploadfile.model.Category;
import com.example.producuploadfile.model.Product;
import com.example.producuploadfile.service.CategoryService;
import com.example.producuploadfile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    Environment environment;

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> allCategory() {
        return categoryService.findAll();
    }
git
    @GetMapping
    public ModelAndView showList(Optional<String> s, @PageableDefault(size = 3) Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("product/list");
        Page<Product> products = s.isPresent()?productService.search(s.get(), pageable):productService.findAll(pageable);
        modelAndView.addObject("keyword", s.orElse(null));
        modelAndView.addObject("products", products);
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute Product productForm) {
        MultipartFile multipartFile = productForm.getImgFile();
        String img = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("file_upload").toString();
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + img));
        } catch (IOException e) {
            e.printStackTrace();
        }
        productForm.setImg(img);
        productService.save(productForm);
        ModelAndView modelAndView = new ModelAndView("/product/create");
        modelAndView.addObject("product", new Product());
        modelAndView.addObject("message", "New Product created successfully!");
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("/product/edit");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("/error");
            return modelAndView;
        }
    }

    @PostMapping("/edit")
    public ModelAndView updateProduct(@ModelAttribute("product") Product product) {
        if (!product.getImgFile().isEmpty()) {
            MultipartFile multipartFile = product.getImgFile();
            String img = multipartFile.getOriginalFilename();
            String fileUpload = environment.getProperty("file_upload").toString();
            try {
                FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + img));
            } catch (IOException e) {
                e.printStackTrace();
            }
            product.setImg(img);
        }
        productService.save(product);
        ModelAndView modelAndView = new ModelAndView("product/edit");
        modelAndView.addObject("product", product);
        modelAndView.addObject("message", "Update successfully");
        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public ModelAndView showDeleteForm(@PathVariable Long id) {
        Product product = productService.findById(id);
        if (product != null) {
            ModelAndView modelAndView = new ModelAndView("product/delete");
            modelAndView.addObject("product", product);
            return modelAndView;
        } else {
            ModelAndView modelAndView = new ModelAndView("error");
            return modelAndView;
        }

    }

    @PostMapping("/delete")
    public String deleteProduct(@ModelAttribute("product") Product product) {
        productService.delete(product.getPId());
        return "redirect:/products";
    }

}

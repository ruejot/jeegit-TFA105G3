<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Nest Dashboard</title>
        <meta http-equiv="x-ua-compatible" content="ie=edge" />
        <meta name="description" content="" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <meta property="og:title" content="" />
        <meta property="og:type" content="" />
        <meta property="og:url" content="" />
        <meta property="og:image" content="" />
        <!-- Favicon -->
        <link rel="shortcut icon" type="image/x-icon" href="assets/imgs/theme/favicon.svg" />
        <!-- Template CSS -->
        <link href="assets/css/main.css" rel="stylesheet" type="text/css" />
    </head>
    <jsp:include page="userHeader.jsp"></jsp:include>
    <body>
        <main>
             <section class="content-main">
                <div class="content-header">
                </div>
                <div class="card mb-4">
                    <div class="card-header bg-brand-2" style="height: 150px"></div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-xl col-lg flex-grow-0" style="flex-basis: 230px">
                                <div class="img-thumbnail shadow w-100 bg-white position-relative text-center" style="height: 190px; width: 200px; margin-top: -120px">
                                    <img src="assets/imgs/brands/vendor-2.png" class="center-xy img-fluid" alt="Logo Brand" />
                                </div>
                            </div>
                            <!--  col.// -->
                            <div class="col-xl col-lg">
                                <h3>Noodles Co.</h3>
                                <p>3891 Ranchview Dr. Richardson, California 62639</p>
                            </div>
                            <!--  col.// -->
                            <div class="col-xl-4 text-md-end">
                                <select class="form-select w-auto d-inline-block">
                                    <option>Actions</option>
                                    <option>Disable shop</option>
                                    <option>Analyze</option>
                                    <option>Something</option>
                                </select>
                                <a href="#" class="btn btn-primary"> View live <i class="material-icons md-launch"></i> </a>
                            </div>
                            <!--  col.// -->
                        </div>
                        <!-- card-body.// -->
                        <hr class="my-4" />
                        <div class="row g-4">
                            <div class="col-md-12 col-lg-4 col-xl-2">
                                <article class="box">
                                    <p class="mb-0 text-muted">Total sales:</p>
                                    <h5 class="text-success">238</h5>
                                    <p class="mb-0 text-muted">Revenue:</p>
                                    <h5 class="text-success mb-0">$2380</h5>
                                </article>
                            </div>
                            <!--  col.// -->
                            <div class="col-sm-6 col-lg-4 col-xl-3">
                                <h6>Contacts</h6>
                                <p>
                                    Manager: Jerome Bell <br />
                                    info@example.com <br />
                                    (229) 555-0109, (808) 555-0111
                                </p>
                            </div>
                            <!--  col.// -->
                            <div class="col-sm-6 col-lg-4 col-xl-3">
                                <h6>Address</h6>
                                <p>
                                    Country: California <br />
                                    Address: Ranchview Dr. Richardson <br />
                                    Postal code: 62639
                                </p>
                            </div>
                            <!--  col.// -->
                            <div class="col-sm-6 col-xl-4 text-xl-end">
                            </div>
                            <!--  col.// -->
                        </div>
                        <!--  row.// -->
                    </div>
                    <!--  card-body.// -->
                </div>
                <!--  card.// -->
                <div class="card mb-4">
                    <div class="card-body">
                        <h3 class="card-title">Products by seller</h3>
                        <div class="row">
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/1.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/2.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/3.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">All Natural Italian-Style</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/4.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Boomchick apop Sweet & Salty</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/5.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/6.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/7.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/8.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Apple Airpods CB-133</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/9.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/10.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/11.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">All Natural Italian-Style</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/12.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Boomchick apop Sweet & Salty</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/1.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/2.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/3.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">All Natural Italian-Style</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/4.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Boomchick apop Sweet & Salty</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/5.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/6.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/7.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/8.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Apple Airpods CB-133</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/9.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/10.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Product name</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/11.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">All Natural Italian-Style</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                            <div class="col-xl-2 col-lg-3 col-md-6">
                                <div class="card card-product-grid">
                                    <a href="#" class="img-wrap"> <img src="assets/imgs/items/12.jpg" alt="Product" /> </a>
                                    <div class="info-wrap">
                                        <a href="#" class="title">Boomchick apop Sweet & Salty</a>
                                        <div class="price mt-1">$179.00</div>
                                        <!-- price-wrap.// -->
                                    </div>
                                </div>
                                <!-- card-product  end// -->
                            </div>
                            <!-- col.// -->
                        </div>
                        <!-- row.// -->
                    </div>
                    <!--  card-body.// -->
                </div>
                <!--  card.// -->
                <div class="pagination-area mt-30 mb-50">
                    <nav aria-label="Page navigation example">
                        <ul class="pagination justify-content-start">
                            <li class="page-item active"><a class="page-link" href="#">01</a></li>
                            <li class="page-item"><a class="page-link" href="#">02</a></li>
                            <li class="page-item"><a class="page-link" href="#">03</a></li>
                            <li class="page-item"><a class="page-link dot" href="#">...</a></li>
                            <li class="page-item"><a class="page-link" href="#">16</a></li>
                            <li class="page-item">
                                <a class="page-link" href="#"><i class="material-icons md-chevron_right"></i></a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </section>
            <!-- content-main end// -->

        </main>
        <script src="assets/js/vendors/jquery-3.6.0.min.js"></script>
        <script src="assets/js/vendors/bootstrap.bundle.min.js"></script>
        <script src="assets/js/vendors/select2.min.js"></script>
        <script src="assets/js/vendors/perfect-scrollbar.js"></script>
        <script src="assets/js/vendors/jquery.fullscreen.min.js"></script>
        <!-- Main Script -->
        <script src="assets/js/main.js?v=1.1" type="text/javascript"></script>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

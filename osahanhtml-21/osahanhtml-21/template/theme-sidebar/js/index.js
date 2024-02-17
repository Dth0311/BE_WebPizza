$(document).ready(function () {
  var linkRestaurant = "http://localhost:8088/restaurant";
  var linkCategory = "http://localhost:8088/category";
  var linkmenu = "http://localhost:8088/menu";
  var token = localStorage.getItem("token");
  $.ajax({
    method: "GET",
    url: linkRestaurant,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }).done(function (msg) {
    if (msg.success) {
      $.each(msg.data, function (index, value) {
        var html = `<a href="detail.html?id=${value.id}" class="text-dark text-decoration-none col-xl-4 col-lg-12 col-md-12">
                            <div class="bg-white shadow-sm rounded d-flex align-items-center p-1 mb-4 osahan-list">
                                <div class="bg-light p-3 rounded">
                                    <img src='${linkRestaurant}/files/${ value.image}' class="img-fluid">
                                </div>
                                <div class="mx-3 py-2 w-100">
                                    <p class="mb-2 text-black">${value.title}</p>
                                    <p class="small mb-2">
                                        <i class="mdi mdi-star text-warning mr-1"></i> <span class="font-weight-bold text-dark">${value.rating.toFixed(1)}</span> (873)
                                        <i class="mdi mdi-silverware-fork-knife ml-3 mr-1"></i> ${value.subTitle}
                                    </p>
                                    <p class="mb-0 text-muted d-flex align-items-center"><span class="badge badge-light"><i class="mdi mdi-truck-fast-outline"></i> Free delivery</span></p>
                                </div>
                            </div>
                        </a>`;
        $("#feature-restaurant").append(html);
      });
    }
  });

  // load menu category

  $.ajax({
    method: "GET",
    url: linkCategory,
    headers: {
      Authorization: `Bearer ${token}`,
    },
  }).done(function (msg) {
    if (msg.success) {
      $.each(msg.data, function (index, value) {
        if(value.menuDTOS.length > 0){
            // load category
            var htmlcategory = `<div class="d-flex align-items-center justify-content-between mb-3 mt-2">
                                    <h5 class="mb-0">${value.name_cate}</h5>
                                    <a href="listing.html" class="small font-weight-bold text-dark">See all <i class="mdi mdi-chevron-right mr-2"></i></a>
                                </div> `;
            htmlcategory += `<div class="row">`;

            $.each(value.menuDTOS, function (index, data) {
            htmlcategory += `<a href="#" class="text-decoration-none col-xl-4 col-md-4 mb-4" data-toggle="modal" data-target="#myitemsModal">
                                <img src='${linkmenu}/files/${data.image}' class="img-fluid rounded" style="width: 100%; height: 80%;">
                                <div class="d-flex align-items-center mt-3">
                                    <p class="text-black h6 m-0">${data.title}</p>
                                    <span class="badge badge-light ml-auto"><i class="mdi mdi-truck-fast-outline"></i> Free delivery</span>
                                </div>
                            </a>`;
            });
            htmlcategory += `</div>`;
            $("#body-osahaneat").append(htmlcategory);
        }
      });
    }
  });
});

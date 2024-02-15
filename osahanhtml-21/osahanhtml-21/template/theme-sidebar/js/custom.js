$(document).ready(function () {
  $("#btn-signin").on("click", function () {

    var email = $("#email").val();
    var password = $("#password").val();

    $.ajax({
      method: "POST",
      url: "http://localhost:8088/login/signin",
      data: {
        userName: email,
        password: password,
      },
    }).done(function (msg) {
      console.log(msg)
      if (msg.success) {
        localStorage.setItem("token",msg.data)
        window.location.href = "index.html";
      } else {
        alert("False");
      }
    });
  });
});

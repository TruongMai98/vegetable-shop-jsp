<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container">
        <div class="container">
            <a class="navbar-brand" href="#">Vegetable Shop</a>
            <button class="navbar-toggler position-relative start-50" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        </div>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <c:url value="/cart" var="cart"/>
                    <a class="nav-link active" aria-current="page" href="${cart}">Cart</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="orders.jsp">Orders</a>
                </li>
                <c:if test="${empty sessionScope.user}">
                    <li class="nav-item">
                        <c:url value="/login" var="login"/>
                        <a class="nav-link active" aria-current="page" href="${login}">Login</a>
                    </li>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                ${fn:toUpperCase(sessionScope.user.name)}
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                            <li><a class="dropdown-item" href="#">Account</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li>
                                <c:url value="/logout" var="logout"/>
                                <a class="dropdown-item" href="${logout}">Logout</a>
                            </li>
                        </ul>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="result_controller" method="post">
    <label for="word">Word</label>
    <input id="word" type="text" name="word" value="laboratory">

    <label for="size">Size</label>
    <input id="size" type="number" name="size" value="3">

    <label for="language">Language</label>
    <select name="language" id="language">
        <option value="english">English</option>
        <option value="romanian">Romana</option>
    </select>

    <button type="submit" id="send-button" disabled>Send</button>
</form>

<form>
    <div style="display: flex; flex-direction: column">
        <label for="captcha">Captcha</label>
        <div><img id="captcha" src="http://localhost:8080/Gradle___com_example___lab_02_1_0_SNAPSHOT_war/image?name=<%= request.getAttribute("captcha") %>" alt="<%= request.getAttribute("captcha") %>"></div>
    </div>

    <div>
        <label for="captcha-input">Enter text</label>
        <input type="text" id="captcha-input">
    </div>
</form>

<script type="application/javascript">

    const sendButton = document.getElementById('send-button');
    const captchaInput = document.getElementById('captcha-input');
    const captchaImage = document.getElementById('captcha');
    const captchaValue = captchaImage.getAttribute("alt").replace('_', ' ').split(".")[0];

    captchaInput.addEventListener('keyup', () => {
        const value = captchaInput.value;
        sendButton.disabled = !(value === captchaValue);
    });

</script>

</body>
</html>

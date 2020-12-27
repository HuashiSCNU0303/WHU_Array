export default {
  convertEmptyAndNewline (string) {
    string = string.replace(/\s/g, "&nbsp;");
    string = string.replace(/\r\n/g, "<br>")
    string = string.replace(/\n/g, "<br>");
    return string;
  },
};

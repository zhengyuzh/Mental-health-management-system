if (typeof jQuery !== "undefined" && typeof saveAs !== "undefined") {
    (function ($) {
        $.fn.wordExport = function (fileName) {
            fileName = typeof fileName !== 'undefined' ? fileName : "jQuery-Word-Export";
            var statics = {
                mhtml: {
                    top: "Mime-Version: 1.0\nContent-Base: " + location.href + "\nContent-Type: Multipart/related; boundary=\"NEXT.ITEM-BOUNDARY\";type=\"text/html\"\n\n--NEXT.ITEM-BOUNDARY\nContent-Type: text/html; charset=\"utf-8\"\nContent-Location: " + location.href + "\n\n<!DOCTYPE html>\n<html>\n_html_</html>",
                    head: "<head>\n<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n<style>\n_styles_\n</style>\n</head>\n",
                    body: "<body>_body_</body>"
                }
            };
            var options = {
                maxWidth: 624
            };
            // Clone selected element before manipulating it
            var markup = $(this).clone();

            // Remove hidden elements from the output
            markup.each(function () {
                var self = $(this);
                if (self.is(':hidden'))
                    self.remove();
            });

            // Embed all images using Data URLs
            var images = Array();
            var img = markup.find('img');
            for (var i = 0; i < img.length; i++) {
                // Calculate dimensions of output image
                var w = Math.min(img[i].width, options.maxWidth);
                var h = img[i].height * (w / img[i].width);
                // Create canvas for converting image to data URL
                var canvas = document.createElement("CANVAS");
                canvas.width = w;
                canvas.height = h;
                // Draw image to canvas
                var context = canvas.getContext('2d');
                context.drawImage(img[i], 0, 0, w, h);
                // Get data URL encoding of image
                var uri = canvas.toDataURL("image/png/jpg");
                $(img[i]).attr("src", img[i].src);
                img[i].width = w;
                img[i].height = h;
                // Save encoded image to array
                images[i] = {
                    type: uri.substring(uri.indexOf(":") + 1, uri.indexOf(";")),
                    encoding: uri.substring(uri.indexOf(";") + 1, uri.indexOf(",")),
                    location: $(img[i]).attr("src"),
                    data: uri.substring(uri.indexOf(",") + 1)
                };
            }

            // Prepare bottom of mhtml file with image data
            var mhtmlBottom = "\n";
            for (var i = 0; i < images.length; i++) {
                mhtmlBottom += "--NEXT.ITEM-BOUNDARY\n";
                mhtmlBottom += "Content-Location: " + images[i].location + "\n";
                mhtmlBottom += "Content-Type: " + images[i].type + "\n";
                mhtmlBottom += "Content-Transfer-Encoding: " + images[i].encoding + "\n\n";
                mhtmlBottom += images[i].data + "\n\n";
            }
            mhtmlBottom += "--NEXT.ITEM-BOUNDARY--";

            //TODO: load css from included stylesheet

            //var styles=' /* Font Definitions */@font-face{font-family:宋体;panose-1:2 1 6 0 3 1 1 1 1 1;mso-font-alt:SimSun;mso-font-charset:134;mso-generic-font-family:auto;mso-font-pitch:variable;mso-font-signature:3 680460288 22 0 262145 0;}  @font-face{font-family:"Cambria Math";panose-1:2 4 5 3 5 4 6 3 2 4;mso-font-charset:1;mso-generic-font-family:roman;mso-font-format:other;mso-font-pitch:variable;mso-font-signature:0 0 0 0 0 0;}  @font-face{font-family:"\@宋体";panose-1:2 1 6 0 3 1 1 1 1 1;mso-font-charset:134;mso-generic-font-family:auto;mso-font-pitch:variable;mso-font-signature:3 680460288 22 0 262145 0;}/* Style Definitions */p.MsoNormal, li.MsoNormal, div.MsoNormal{mso-style-unhide:no;mso-style-qformat:yes;mso-style-parent:"";margin:0cm;margin-bottom:.0001pt;mso-pagination:widow-orphan;font-size:14.0pt;font-family:宋体;mso-bidi-font-family:宋体;}p.MsoHeader, li.MsoHeader, div.MsoHeader{mso-style-noshow:yes;mso-style-priority:99;mso-style-link:"页眉 Char";margin:0cm;margin-bottom:.0001pt;text-align:center;mso-pagination:widow-orphan;layout-grid-mode:char;font-size:9.0pt;font-family:宋体;mso-bidi-font-family:宋体;}p.MsoFooter, li.MsoFooter, div.MsoFooter{mso-style-noshow:yes;mso-style-priority:99;mso-style-link:"页脚 Char";margin:0cm;margin-bottom:.0001pt;mso-pagination:widow-orphan;layout-grid-mode:char;font-size:9.0pt;font-family:宋体;mso-bidi-font-family:宋体;}p.MsoAcetate, li.MsoAcetate, div.MsoAcetate{mso-style-noshow:yes;mso-style-priority:99;mso-style-link:"批注框文本 Char";margin:0cm;margin-bottom:.0001pt;mso-pagination:widow-orphan;font-size:9.0pt;font-family:宋体;mso-bidi-font-family:宋体;}span.Char{mso-style-name:"页眉 Char";mso-style-noshow:yes;mso-style-priority:99;mso-style-unhide:no;mso-style-locked:yes;mso-style-link:页眉;font-family:宋体;mso-ascii-font-family:宋体;mso-fareast-font-family:宋体;mso-hansi-font-family:宋体;}span.Char0{mso-style-name:"页脚 Char";mso-style-noshow:yes;mso-style-priority:99;mso-style-unhide:no;mso-style-locked:yes;mso-style-link:页脚;font-family:宋体;mso-ascii-font-family:宋体;mso-fareast-font-family:宋体;mso-hansi-font-family:宋体;}span.Char1{mso-style-name:"批注框文本 Char";mso-style-noshow:yes;mso-style-priority:99;mso-style-unhide:no;mso-style-locked:yes;mso-style-link:批注框文本;font-family:宋体;mso-ascii-font-family:宋体;mso-fareast-font-family:宋体;mso-hansi-font-family:宋体;}p.msochpdefault, li.msochpdefault, div.msochpdefault{mso-style-name:msochpdefault;mso-style-unhide:no;mso-margin-top-alt:auto;margin-right:0cm;mso-margin-bottom-alt:auto;margin-left:0cm;mso-pagination:widow-orphan;font-size:10.0pt;font-family:宋体;mso-bidi-font-family:宋体;}span.msonormal0{mso-style-name:msonormal;mso-style-unhide:no;}.MsoChpDefault{mso-style-type:export-only;mso-default-props:yes;font-size:10.0pt;mso-ansi-font-size:10.0pt;mso-bidi-font-size:10.0pt;mso-ascii-font-family:"Times New Roman";mso-hansi-font-family:"Times New Roman";mso-font-kerning:0pt;}/* Page Definitions */  @page WordSection1{size:595.3pt 841.9pt;margin:72.0pt 90.0pt 72.0pt 90.0pt;mso-header-margin:42.55pt;mso-footer-margin:49.6pt;mso-paper-source:0;}div.WordSection1{page:WordSection1;}';

            var styles = "";

            // Aggregate parts of the file together
            var fileContent = statics.mhtml.top.replace("_html_", statics.mhtml.head.replace("_styles_", styles) + statics.mhtml.body.replace("_body_", markup.html())) + mhtmlBottom;

            // Create a Blob with the file contents
            var blob = new Blob([fileContent], {
                type: "application/msword;charset=utf-8"
            });
            saveAs(blob, fileName + ".doc");
        };
    })(jQuery);
} else {
    if (typeof jQuery === "undefined") {
        console.error("jQuery Word Export: missing dependency (jQuery)");
    }
    if (typeof saveAs === "undefined") {
        console.error("jQuery Word Export: missing dependency (FileSaver.js)");
    }
}
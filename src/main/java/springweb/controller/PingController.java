package springweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;
import java.util.Map;

@Controller
public class PingController {

    @Autowired
    private PingService pingService;

    @RequestMapping(value="/ping/{tag}", produces="text/plain")
    @ResponseBody
    public String pingTag(@PathVariable("tag") String tag) {
        pingService.insert(tag);
        return "Ping tag '" + tag + "' has been inserted. ";
    }

    @RequestMapping(value="/pings", produces="text/plain")
    @ResponseBody
    public String pings() {
        List<Map<String, Object>> result = pingService.findAllPings();
  if (result.size() == 0)
   return "No record found.";

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> row : result) {
            sb.append("Ping" + row).append("\n");
        }
        return sb.toString();
    }
}
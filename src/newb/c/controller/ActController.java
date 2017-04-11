package newb.c.controller;

import java.util.HashMap;  
import java.util.Map;  
  
  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
  
  
import org.activiti.editor.constants.ModelDataJsonConstants;  
import org.activiti.engine.RepositoryService;  
import org.activiti.engine.repository.Model;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;  
import org.springframework.web.bind.annotation.ResponseBody;  
  
  
import com.fasterxml.jackson.databind.ObjectMapper;  
import com.fasterxml.jackson.databind.node.ObjectNode;  
  
@Controller  
public class ActController {  
    @Autowired  
    private RepositoryService repositoryService;  
  
    /** 
     * 查询生日列表 
     *  
     * @param req 
     * @return 
     */  
    @RequestMapping(value = "/activiti.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")  
    @ResponseBody  
    public Object brithdayList(HttpServletRequest req) {  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("name", "activiti");  
        return map;  
    }  
  
  
    @RequestMapping(value = "/create.do", method = RequestMethod.GET, produces = "application/json;charset=utf-8")  
    public void create(  @RequestParam("name") String name,  @RequestParam("key") String key,   @RequestParam(value = "description", required = false) String description,  HttpServletRequest request, HttpServletResponse response) {  
        try {  
            ObjectMapper objectMapper = new ObjectMapper();  
            ObjectNode modelObjectNode = objectMapper.createObjectNode();  
            modelObjectNode.put(ModelDataJsonConstants.MODEL_NAME, name);  
            modelObjectNode.put(ModelDataJsonConstants.MODEL_REVISION, 1);  
            modelObjectNode.put(ModelDataJsonConstants.MODEL_DESCRIPTION,  
                    org.apache.commons.lang3.StringUtils  
                            .defaultString(description));  
            Model newModel = repositoryService.newModel();  
            newModel.setMetaInfo(modelObjectNode.toString());  
            newModel.setName(name);  
            newModel.setKey(key);  
            repositoryService.saveModel(newModel);  
            ObjectNode editorNode = objectMapper.createObjectNode();  
            editorNode.put("id", "canvas");  
            editorNode.put("resourceId", "canvas");  
            ObjectNode stencilSetNode = objectMapper.createObjectNode();  
            stencilSetNode.put("namespace",  "http://b3mn.org/stencilset/bpmn2.0#");  
            editorNode.put("stencilset", stencilSetNode);  
            repositoryService.addModelEditorSource(newModel.getId(), editorNode.toString().getBytes("utf-8"));  
            response.sendRedirect(request.getContextPath()+ "/service/editor?id=" + newModel.getId());  
        } catch (Exception e) {  
            e.getStackTrace();  
        }
//		return null;  
    }  
}  

package com.goodchobo.common.util;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.goodchobo.common.util.jsonview.JsonView;
import com.goodchobo.common.util.jsonview.JsonViewModule;
import com.goodchobo.common.util.jsonview.Match;

public class JsonViewUtil {
	private static JSONParser jsonParser;
	private static ObjectMapper mapper;

	public static JSONParser getJsonParser() {
		if(jsonParser == null) {
			jsonParser = new JSONParser();
		}

		return jsonParser;
	}

	public static ObjectMapper getMapper() {
		if(mapper == null) {
			mapper = new ObjectMapper().registerModule(new JsonViewModule());
			mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		}

		return mapper;
	}

	public static <T> Object filterListModel(Class<T> clazz, ArrayList<T> defList, Match match) throws Exception {
		JsonView<ArrayList<T>> viwevedObjectList = JsonView
				.with(CommonUtil.null2EmptyList(defList))
				.onClass(clazz, match);

		return getJsonParser().parse(getMapper().writeValueAsString(viwevedObjectList));
	}

	public static <T> Object filterListModel(Class<T> clazz, List<T> defList, Match match) throws Exception {
		JsonView<List<T>> viwevedObjectList = JsonView
				.with(CommonUtil.null2EmptyList(defList))
				.onClass(clazz, match);

		return getJsonParser().parse(getMapper().writeValueAsString(viwevedObjectList));
	}

	public static <T> Object filterModel(Class<T> clazz, T defModel, Match match) throws Exception {
		JsonView<T> viwevedObjectList = JsonView
				.with(defModel)
				.onClass(clazz, match);

		return getJsonParser().parse(getMapper().writeValueAsString(viwevedObjectList));
	}

	public static Match getMapperPointListFilters() {
		return  Match.match()
				.exclude("*")
				.include("id")
				.include("name")
				.include("userId")
				.include("pointLogList.id")
				.include("pointLogList.plusPoint")
				.include("pointLogList.minusPoint");
	}

}

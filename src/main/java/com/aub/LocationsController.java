package com.aub;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.websocket.server.PathParam;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller for locations api.
 * 
 * @author haribabu_r
 *
 */
@RestController
@RequestMapping("/systems/v1")
@Api(value = "locations", description = "Operations pertaining to locations")
public class LocationsController {

	private static final Logger LOG = LogManager.getLogger(LocationsController.class.getName());

	/***
	 * To get the locations by address (city/zip/state)
	 * 
	 * @param address
	 * @param limit
	 * @param filters
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value = "To get the locations by address (city/zip/state)")
	@RequestMapping(method = RequestMethod.GET, value = "locations/address")
	@ResponseBody
	public String getLocationsByAddress(@RequestParam("address") String address,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "filters", required = false) String filters) throws IOException {
		return getData(limit == 1 ? "locations1.json" : "locations2.json");
	}

	/***
	 * To get the locations by latitude and longitude
	 * 
	 * @param latitude
	 * @param longitude
	 * @param limit
	 * @param filters
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value = "To get the locations by latitude and longitude")
	@RequestMapping(method = RequestMethod.GET, value = "locations/coordinates")
	@ResponseBody
	public String getLocationsByCoordinates(@RequestParam("lat") double latitude,
			@RequestParam("long") double longitude, @RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "filters", required = false) String filters) throws IOException {
		return getData(limit == 1 ? "locations1.json" : "locations2.json");
	}

	/***
	 * To get the location details by id.
	 * 
	 * @param id
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value = "To get the location details by id.")
	@RequestMapping(method = RequestMethod.GET, value = "location/{id}")
	@ResponseBody
	public String getLocationById(@PathParam("id") String id) throws IOException {
		return getData("location.json");
	}

	/**
	 * To get all the filters applicable for list locations
	 * 
	 * @return
	 * @throws IOException
	 */
	@ApiOperation(value = "To get all the filters applicable for list locations.")
	@RequestMapping(method = RequestMethod.GET, value = "location/filters")
	@ResponseBody
	public String getLocationFilters() throws IOException {
		return getData("filters.json");
	}

	/**
	 * To get a file by file name
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	private String getData(String fileName) throws IOException {
		LOG.info("Inside system service");
		String data;
		ClassPathResource cpr = new ClassPathResource(fileName);
		byte[] bdata = FileCopyUtils.copyToByteArray(cpr.getInputStream());
		data = new String(bdata, StandardCharsets.UTF_8);
		return data;
	}
}

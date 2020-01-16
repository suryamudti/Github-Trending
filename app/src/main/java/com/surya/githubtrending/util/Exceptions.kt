package com.surya.githubtrending.util

import java.io.IOException

/**
 * Created by suryamudti on 12/11/2019.
 */

class ApiException(message: String) : IOException(message)
class NoInternetException(message: String) : IOException(message)
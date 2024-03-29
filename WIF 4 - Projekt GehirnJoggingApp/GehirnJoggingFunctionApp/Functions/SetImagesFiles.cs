using System;
using System.IO;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Azure.WebJobs;
using Microsoft.Azure.WebJobs.Extensions.Http;
using Microsoft.AspNetCore.Http;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Net.Http;

namespace AzureFunctions.Functions
{
    public static class SetImagesFiles
    {
        [FunctionName("SetImagesFiles")]
        public static HttpResponseMessage Run([HttpTrigger(AuthorizationLevel.Anonymous, "get", "post", Route = null)] HttpRequest req, ILogger log)
        {
            string requestBody = new StreamReader(req.Body).ReadToEnd();
            Request<ISet<String>> request = JsonConvert.DeserializeObject<Request<ISet<String>>>(requestBody);

            try
            {
                using (SqlConnection con = new SqlConnection(Environment.GetEnvironmentVariable("DB_CONNECTION_STRING")))
                {
                    con.Open();
                    // TOKEN überprüfen
                    HttpResponseMessage message = FUNCTIONS<ISet<String>>.checkToken(request, con);
                    if (message != null) return message;


                    foreach (String file in request.obj)
                    {
                        // Anfrage bearbeiten
                        string sql = "insert imageNutzer values(@ID, @FNAME)";
                        using (SqlCommand com1 = new SqlCommand(sql, con))
                        {
                            com1.Parameters.Add("@ID", System.Data.SqlDbType.Int);
                            com1.Parameters.Add("@FNAME", System.Data.SqlDbType.NVarChar);

                            com1.Parameters["@FNAME"].Value = file;
                            com1.Parameters["@ID"].Value = request.Id;

                            com1.ExecuteNonQuery();
                        }
                    }
                }

                request.Success = true;
            }
            catch (SqlException e)
            {
                request.Success = false;
                request.Error = e.Message;
                request.Errorcode = e.ErrorCode;
            }

            return new HttpResponseMessage
            {
                Content = new StringContent(JsonConvert.SerializeObject(request))
            };
        }
    }
}
